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
    let lastView = localStorage.getItem('lastView');
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
        forceEventDuration: true,
        eventOverlap: false,
        eventTimeFormat: {
            hour: 'numeric',
            minute: '2-digit',
            meridiem: false
        },
        viewDidMount: function(info) {
            localStorage.setItem('lastView', info.view.type);
        },
        initialView: lastView || 'dayGridMonth',
        events: function(fetchInfo, successCallback, failureCallback) {
            fetch('/api/user')
                .then(response => {
                    if (!response.ok) {
                        throw new Error('Request failed');
                    }
                    return response.json();
                })
                .then(data => {
                    localStorage.setItem('userId', data.userId);
                    const userId = localStorage.getItem('userId');
                    fetch('/api/events/users/' + userId)
                        .then(response => response.json())
                        .then(data => {
                            const events = data.map(event => ({
                                id: event.id,
                                title: event.title,
                                start: event.startTime,
                                end: event.endTime,
                                cost: event.cost,
                            }));
                            successCallback(events);
                        })
                        .catch(error => {
                            console.error('Error fetching events', error);
                            failureCallback(error);
                        });
                })
                .catch(error => {
                    console.error('Error getting user id', error);
                });
        },
        eventDrop: function(info) {
            let event = info.event;
            const userId = localStorage.getItem('userId');
            if (!userId) {
                console.error('User id is not set in local storage');
                return;
            }
            let eventId = event.id;
            fetch('/api/events/users/' + userId + "/" +event.id, {
                method: 'PATCH',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify({
                    id: event.id,
                    title: event.title,
                    startTime: event.start.toISOString(),
                    endTime: event.end.toISOString(),
                    cost: event.cost
                })
            })
                .then(response => response.json())
                .then(data => {
                    console.log('Event updated:', data);
                    eventId = "";
                })
                .catch(error => {
                    console.error('Error updating event', error);
                });
        },


        eventDidMount: function(info) {
            let totalCost = 0;
            calendar.getEvents().forEach(function(event) {
                totalCost += event.extendedProps.cost;
            });
            document.getElementById('total').innerHTML = 'Total cost: ' + totalCost;
        },

        eventClick: function(info) {
            let event = info.event;
            $('#eventId').val(event.id);
            $('#eventTitle').val(event.title);
            $('#eventStart').val(event.startStr.substring(0, 19).replace('T', ' '));
            $('#eventEnd').val(event.endStr.substring(0, 19).replace('T', ' '));
            $('#eventCost').val(event.extendedProps.cost);
            $('#eventModal').show();
            $('#deleteEventBtn').show();
        },

        dateClick: function(info) {
            $('#deleteEventBtn').hide();
        },
        select: function(info) {
            let eventStart = info.startStr;
            let eventEnd = info.endStr;
            let eventCost = info.cost;
            $('#eventTitle').val('');
            $('#eventStart').val(eventStart);
            $('#eventEnd').val(eventEnd);
            $('#eventCost').val(eventCost);
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
        let eventCost = $('#eventCost').val();

        if (eventTitle === '' || eventStart === '' || eventEnd === '' || eventCost === '') {
            alert('Please fill in all fields!');
            return;
        }

        const userId = localStorage.getItem('userId');
        if (!userId) {
            console.error('User id is not set in local storage');
            return;
        }

        let httpMethod = 'POST';
        let url = '/api/events/users/' + userId;

        if (eventId) {
            httpMethod = 'PATCH';
            url += '/' + eventId;
        }

        const eventData = {
            id: eventId,
            title: eventTitle,
            startTime: new Date(eventStart).toISOString(),
            endTime: new Date(eventEnd).toISOString(),
            cost: eventCost
        };

        calendar.addEvent(eventData);
        console.log(eventData);
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
                $('#eventId').val('');
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
                $('#eventId').val('');
            },
            error: function(xhr) {
                console.log(xhr.responseText);
            }
        });
    });


    calendar.render();
});
