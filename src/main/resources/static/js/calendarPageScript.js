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
        events: [
            {
                title: 'Event 1',
                start: '2023-02-19T12:00:00',
                end: '2023-02-19T14:00:00'
            },
            {
                title: 'Event 2',
                start: '2023-02-20T10:00:00',
                end: '2023-02-20T12:00:00'
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
                let eventStart = start.format('YYYY-MM-DD HH:mm:ss');
                let eventEnd = end.format('YYYY-MM-DD HH:mm:ss');
                let eventData = {
                    title: title,
                    start: eventStart,
                    end: eventEnd
                };
                calendar.fullCalendar('renderEvent', eventData, true);
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