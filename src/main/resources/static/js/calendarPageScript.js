document.addEventListener('DOMContentLoaded', function() {
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
        eventTimeFormat: { // Для формату 24-годинного формату
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
        eventDidMount: function(info) {
            console.log('Event did mount:', info.event);
        },
        dateClick: function(info) {
            console.log('Date clicked:', info.dateStr);
        },
        // events: [{
        //     id: 1,
        //     title: 'Event 1',
        //     start: '2023-04-01T10:00:00',
        //     end: '2023-04-01T12:00:00'
        // },
        //     {
        //         id: 2,
        //         title: 'Event 2',
        //         start: '2023-04-05T14:00:00',
        //         end: '2023-04-05T16:00:00'
        //     },
        //     {
        //         id: 3,
        //         title: 'All Day Event',
        //         start: '2023-04-08',
        //         allDay: true
        //     }
        // ],
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


    $('#editForm').submit(function(event) {
        event.preventDefault();
        let eventId = $('#eventId').val();
        let eventTitle = $('#eventTitle').val();
        let eventStart = $('#eventStart').val();
        let eventEnd = $('#eventEnd').val();
        eventData = { // присвоєння значення глобальній змінній
            id: eventId,
            title: eventTitle,
            startTime: new Date(eventStart).toISOString(),
            endTime: new Date(eventEnd).toISOString()
        };
        calendar.addEvent(eventData);
        console.log(eventData)
        $('#eventModal').hide();

        $.ajax({
            url: '/api/events', // URL контроллера
            type: 'POST', // метод HTTP запиту
            data: JSON.stringify(eventData), // дані для передачі
            contentType: 'application/json', // тип даних, що надсилаються на сервер
            success: function(response) {
                console.log(response); // виведення відповіді в консолі
                calendar.addEvent(eventData); // додавання івенту до календаря
                $('#eventModal').hide();
            },
            error: function(xhr) {
                console.log(xhr.responseText); // виведення помилки в консолі
            }
        });
    });

    calendar.render();
});

