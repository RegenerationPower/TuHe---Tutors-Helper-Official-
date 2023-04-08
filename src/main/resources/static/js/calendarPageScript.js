document.addEventListener('DOMContentLoaded', function() {
    $('.datetimepicker').datetimepicker({
        format: 'YYYY-MM-DD HH:mm:ss',
        useCurrent: false
    });
    $('#eventStart').datetimepicker({
        format: 'Y-m-d H:i:s'
    });

    $('#eventEnd').datetimepicker({
        format: 'Y-m-d H:i:s'
    });
    let eventData;
    let calendarEl = document.getElementById('calendar');
    let calendar = new FullCalendar.Calendar(calendarEl, {
        headerToolbar: {
            left: 'prev,next today',
            center: 'title',
            right: 'dayGridMonth,timeGridWeek,timeGridDay'
        },
        views: {
            agendaDay: {
                type: 'agenda',
                duration: {
                    days: 1
                }
            }
        },
        selectMirror: true,
        selectable: true,
        editable: true,
        eventOverlap: false,
        eventTimeFormat: {
            hour: 'numeric',
            minute: '2-digit',
            meridiem: false
        },
        events: function(fetchInfo, successCallback, failureCallback) {
            fetch('/api/events')
                .then(response => response.json())
                .then(data => {
                    const events = data.map(event => ({
                        id: event.id,
                        title: event.title,
                        start: event.startTime,
                        end: event.endTime,
                    }));
                    successCallback(events);
                })
                .catch(error => {
                    console.error('Error fetching events', error);
                    failureCallback(error);
                });
        },
        eventDrop: function(info) {
            let event = info.event;
            fetch('/api/events/' + event.id, {
                method: 'PATCH',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify({
                    id: event.id,
                    title: event.title,
                    startTime: event.start.toISOString(),
                    endTime: event.end.toISOString()
                })
            })
                .then(response => response.json())
                .then(data => {
                    console.log('Event updated:', data);
                })
                .catch(error => {
                    console.error('Error updating event', error);
                });
        },

        eventDidMount: function(info) {

        },

        eventClick: function(info) {
            let event = info.event;
            $('#eventId').val(event.id);
            $('#eventTitle').val(event.title);
            $('#eventStart').val(event.startStr.substring(0, 19).replace('T', ' '));
            $('#eventEnd').val(event.endStr.substring(0, 19).replace('T', ' '));
            $('#eventModal').show();
            $('#deleteEventBtn').show();
        },

        dateClick: function(info) {
            $('#deleteEventBtn').hide();
        },
        select: function(info) {
            let eventStart = info.startStr;
            let eventEnd = info.endStr;
            $('#eventTitle').val('');
            $('#eventStart').val(eventStart);
            $('#eventEnd').val(eventEnd);
            $('#eventModal').show();
            calendar.unselect();
        }
    });
    $('.close').click(function() {
        $('#eventModal').hide();
    });


    $('#eventForm').submit(function(event) {
        event.preventDefault();
        let eventId = $('#eventId').val();
        let eventTitle = $('#eventTitle').val();
        let eventStart = $('#eventStart').val();
        let eventEnd = $('#eventEnd').val();

        let url = '/api/events';
        let httpMethod = 'POST';

        if (eventId) {
            url = url + '/' + eventId;
            httpMethod = 'PATCH';
        }

        eventData = {
            id: eventId,
            title: eventTitle,
            startTime: new Date(eventStart).toISOString(),
            endTime: new Date(eventEnd).toISOString()
        };
        calendar.addEvent(eventData);
        console.log(eventData)
        $('#eventModal').hide();

        $.ajax({
            url: url,
            type: httpMethod,
            data: JSON.stringify(eventData),
            contentType: 'application/json',
            success: function(response) {
                console.log(response);
                calendar.refetchEvents();
                $('#eventModal').hide();
            },
            error: function(xhr) {
                console.log(xhr.responseText);
            }
        });
    });

    $('#deleteEventBtn').click(function() {
        let eventId = $('#eventId').val();
        let url = '/api/events/' + eventId;
        $.ajax({
            url: url,
            type: 'DELETE',
            success: function(response) {
                console.log(response);
                calendar.refetchEvents();
                $('#eventModal').hide();
            },
            error: function(xhr) {
                console.log(xhr.responseText);
            }
        });
    });


    calendar.render();
});
