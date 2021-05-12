var stompClient = null;

function setConnected(connected) {
    $("#connect").prop("disabled", connected);
    $("#disconnect").prop("disabled", !connected);
    if (connected) {
        $("#conversation").show();
    }
    else {
        $("#conversation").hide();
    }
    $("#greetings").html("");
}

function websocket(){
    var socket = new WebSocket('ws://localhost:8080/ws');
    ws = Stomp.over(socket);

    ws.connect({}, function(frame) {
        setConnected(true);

        ws.subscribe("/secured/user/queue/error", function(message) { alert("Error " + message.body); });
        ws.subscribe("/secured/user/queue/reply", function(message) { alert("Message " + message.body); });

    }, function(error) {
        alert("STOMP error " + error);
    });
}

function connect() {
    var socket = new SockJS('/ws');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        setConnected(true);

        stompClient.subscribe('/topic/greetings', function (greeting) {
            showGreeting(JSON.parse(greeting.body).name);
        });

        var url = stompClient.ws._transport.url;
        showGreeting(url)
        url = url.replace("ws://localhost:8080/ws/",  "");
        url = url.replace("/websocket", "");
        url = url.replace(/^[0-9]+\//, "");
        showGreeting("Your current session is: " + url)
        sessionId = url;
        stompClient.subscribe('/secured/user/queue/reply-user'+sessionId , function (data) {
            var msg = JSON.parse(data.body);
            showMessage(msg.author, msg.message);
        }, function(error) {
            showMessage("STOMP error " + error);
        });
        stompClient.subscribe('/secured/user/queue/error-user'+sessionId, function (error) {
                    showGreeting("error user: "+error.body);
                });

        stompClient.subscribe('/secured/user/queue/sent', function (data) {
            showMessage("/secured/user/queue/sent");
            var msg = JSON.parse(data.body);
            showMessage(msg.author, msg.message);
        }, function(error) {
            showMessage("STOMP error " + error);
        });
    }, function(error) {
        showMessage("Connect error " + error);
    });
}

function disconnect() {
    if (stompClient !== null) {
        stompClient.disconnect();
    }
    setConnected(false);
    console.log("Disconnected");
}

function send() {
    stompClient.send("/app/secured/room/send", {}, JSON.stringify({'name': $("#name").val()}));
}

function reply() {
    stompClient.send("/app/secured/room/reply", {}, JSON.stringify({'name': $("#name").val()}));
}

function sayHello() {
    stompClient.send("/app/hello", {}, JSON.stringify({'name': $("#name").val()}));
}

function showGreeting(message) {
    $("#greetings").append("<tr><td>" + message + "</td></tr>");
}

function showMessage(author, message) {
    $("#greetings").append("<tr><td>" + author + "--" + message + "</td></tr>");
}

$(function () {
    $("form").on('submit', function (e) {
        e.preventDefault();
    });
    $( "#connect" ).click(function() { connect(); });
    $( "#disconnect" ).click(function() { disconnect(); });
    $( "#send" ).click(function() { send(); });
    $( "#reply" ).click(function() { reply(); });
    $( "#hello" ).click(function() { sayHello(); });
});