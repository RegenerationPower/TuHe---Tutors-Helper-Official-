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
            fetch('/api/users')
                .then(response => {
                    if (!response.ok) {
                        throw new Error('Request failed');
                    }
                    return response.json();
                })
                .then(data => {
                    localStorage.setItem('userId', data.id);
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
                                paid: event.paid,
                                studentId: event.student ? event.student.id : null,
                                className: (event.paid === 1 || event.paid === true) ? 'fc-h-event event-green fc-event-main' : ''
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
                    cost: event.extendedProps.cost,
                    paid: event.extendedProps.paid ? 1 : 0
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
            let paidCost = 0;
            const activeStartDate = calendar.view.activeStart;
            const activeEndDate = calendar.view.activeEnd;

            calendar.getEvents().forEach(function(event) {
                const eventStartDate = event.start;
                const eventEndDate = event.end || event.start;

                if (eventEndDate >= activeStartDate && eventStartDate <= activeEndDate) {
                    totalCost += event.extendedProps.cost;

                    if (event.extendedProps.paid === 1 || event.extendedProps.paid === true) {
                        paidCost += event.extendedProps.cost;
                    }
                }
            });

            document.getElementById('total-cost').innerHTML = 'Total cost: ' + totalCost;
            document.getElementById('paid-cost').innerHTML = 'Paid cost: ' + paidCost;
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
            const paid = info.event.extendedProps.paid;
            if (paid === 1 || paid === true) {
                $('#eventPaid').prop('checked', true);
            } else {
                $('#eventPaid').prop('checked', false);
            }
            const studentId = event.extendedProps.studentId;
            if (studentId === null || studentId === undefined) {
                $('#studentList').val('none');
            } else {
                $('#studentList').val(studentId);
            }

            calendar.refetchEvents();
        },

        dateClick: function(info) {
            $('#deleteEventBtn').hide();
        },

        select: function(info) {
            let eventStart = info.startStr;
            let eventEnd = info.endStr;
            let eventCost = info.cost;
            let eventPaid = info.paid;
            let studentId = info.studentId;
            $('#eventTitle').val('');
            $('#eventStart').val(eventStart);
            $('#eventEnd').val(eventEnd);
            $('#eventCost').val(eventCost);
            $('#eventPaid').prop('checked', false);
            $('#studentList').val('none');
            $('#eventModal').show();
            calendar.unselect();
            calendar.refetchEvents();
        }
    });

    $('.close').click(function() {
        $('#eventModal').hide();
    });

    $('#eventForm').submit(function(event) {
        event.preventDefault();
        let eventTitle = $('#eventTitle').val();
        let eventStart = $('#eventStart').val();
        let eventEnd = $('#eventEnd').val();
        let eventCost = $('#eventCost').val();
        let eventPaid = $('#eventPaid').is(':checked');
        let studentId = $('#studentList').val();

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

        const eventId = $('#eventId').val();

        if (eventId) {
            httpMethod = 'PATCH';
            url += '/' + eventId;
        }

        if (studentId === 'none') {
            studentId = null;
        }

        const eventData = {
            id: eventId,
            title: eventTitle,
            startTime: new Date(eventStart).toISOString(),
            endTime: new Date(eventEnd).toISOString(),
            cost: eventCost,
            paid: eventPaid ? 1 : 0,
            studentId: studentId
        };

        $.ajax({
            url: url,
            type: httpMethod,
            data: JSON.stringify(eventData),
            contentType: 'application/json',
            success: function(response) {
                console.log(response);
                calendar.addEvent(eventData);
                calendar.refetchEvents();
                $('#eventModal').hide();
                $('#eventId').val('');
            },
            error: function(xhr) {
                console.log(xhr.responseText);
            }
        });

    });

    calendar.on('datesSet', function(info) {
        document.getElementById('total-cost').innerHTML = 'Total cost:';
        document.getElementById('paid-cost').innerHTML = 'Paid cost:';
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

document.getElementById('logout-button').addEventListener('click', function() {
    localStorage.clear();
    window.location.href = '/logout';
});

const popupTrigger = document.querySelector('.popup-trigger');
const popupWindow = document.querySelector('.popup-window');
const closeButton = document.querySelector('.close-button');

const closePopup = function () {
    popupWindow.style.opacity = '0';

    setTimeout(function () {
        popupWindow.style.visibility = 'hidden';
    }, 300);
};

popupTrigger.addEventListener('click', function() {
    popupWindow.style.visibility = 'visible';
    popupWindow.style.opacity = '1';
});

document.addEventListener('click', function(event) {
    if (!popupTrigger.contains(event.target) && !popupWindow.contains(event.target)) {
        closePopup();
    }
});

closeButton.addEventListener('click', function() {
    closePopup();
});

loadStudents();

function loadStudents() {
    const userId = localStorage.getItem('userId');
    if (!userId) {
        console.error('User id is not set in local storage');
        return;
    }

    fetch(`/api/students/users/${userId}`)
        .then(response => response.json())
        .then(students => {
            const studentList = document.getElementById('studentList');

            while (studentList.firstChild) {
                studentList.removeChild(studentList.firstChild);
            }

            const noneOption = document.createElement('option');
            noneOption.value = 'none';
            noneOption.textContent = 'None';
            studentList.appendChild(noneOption);

            students.forEach(student => {
                const option = document.createElement('option');
                option.value = student.id;
                option.textContent = student.studentName;
                studentList.appendChild(option);
            });
        })
        .catch(error => {
            console.error('Error loading students:', error);
        });
}


const addStudentButton = document.getElementById('add-student-button');
const studentInput = document.getElementById('student-input');

addStudentButton.addEventListener('click', () => {
    const userId = localStorage.getItem('userId');
    const studentName = studentInput.value;

    const studentData = {
        studentName: studentName,
        user: userId
    };

    fetch(`/api/students/users/${userId}`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(studentData)
    })
        .then(response => response.json())
        .then(student => {
            console.log('Created student:', student);
            loadStudents();
        })
        .catch(error => {
            console.error('Error creating student:', error);
        });
});

