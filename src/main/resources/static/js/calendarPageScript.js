$(document).ready(function() {
    let calendar = $('#calendar').fullCalendar({
        header: {
            left: 'prev,next today',
            center: 'title',
            right: 'month,agendaWeek,agendaDay'
        },
        views: {
            agendaDay: {
                type: 'agenda',
                duration: { days: 1 }
            }
        },
        defaultView: 'month',
        selectable: true,
        editable: true,
        eventOverlap: false,
        // events: '/api/events/',
        events: [
            {
                title: 'Event 1',
                start: '2023-03-01T10:00:00',
                end: '2023-03-01T12:00:00'
            },
            {
                title: 'Event 2',
                start: '2023-03-05T14:00:00',
                end: '2023-03-05T16:00:00'
            },
            {
                title: 'All Day Event',
                start: '2023-03-08',
                allDay: true
            }
        ],
        eventClick: function(calEvent, jsEvent, view) {
            $('#eventId').val(calEvent.id);
            $('#eventTitle').val(calEvent.title);
            $('#eventStart').val(calEvent.start.format('YYYY-MM-DD HH:mm:ss'));
            $('#eventEnd').val(calEvent.end.format('YYYY-MM-DD HH:mm:ss'));
            $('#eventModal').show();
        },
        select: function(start, end, jsEvent, view) {
            let title = prompt('Enter Event Title:');
            if (title) {
                let eventStart = start.format('YYYY-MM-DDTHH:mm:ss');
                let eventEnd = end.format('YYYY-MM-DDTHH:mm:ss');
                let eventData = {
                    title: title,
                    startTime: eventStart,
                    endTime: eventEnd
                };
                $.ajax({
                    url: '/api/events/',
                    method: 'POST',
                    data: eventData,
                    success: function(response) {
                        let calendarEventEntity = {
                            id: response.id,
                            title: response.title,
                            startTime: moment(response.startTime),
                            endTime: moment(response.endTime)
                        };
                        calendar.fullCalendar('renderEvent', calendarEventEntity, true);
                    }
                });
            }
            calendar.fullCalendar('unselect');
        }

    });

    $('.close').click(function() {
        $('#eventModal').hide();
    });

    $('#editForm').submit(function(event) {
        event.preventDefault();
        let eventId = $('#eventId').val();
        let eventTitle = $('#eventTitle').val();
        let eventStart = $('#eventStart').val();
        let eventEnd = $('#eventEnd').val();
        let eventData = {
            id: eventId,
            title: eventTitle,
            start: eventStart,
            end: eventEnd
        };
        let updatedEvent = calendar.getEventById(eventData.id);
        updatedEvent.setProp('title', eventData.title);
        updatedEvent.setStart(eventData.start);
        updatedEvent.setEnd(eventData.end);
        calendar.updateEvent(updatedEvent);
        $('#eventModal').hide();
    });
});
