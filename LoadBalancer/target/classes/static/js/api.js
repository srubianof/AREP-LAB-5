var api = (function () {
    var endP = '/messages';

    function addMessage() {
        var mensaje = document.getElementById("message").value;
        console.log(mensaje)
        axios.post(endP, {"Message":mensaje,"Date":""})
            .then(res => {
                    getMessages()
                }
            )
    }
    function getMessages() {
        $("#messagesTable > tbody").empty();
        axios.get(endP).then(res => {
            console.log(res.data)
            res.data.map(message => {
                console.log(message)
                $("#messagesTable > tbody").append(
                    "<tr>" +
                    " <td>" + message.Message + "</td>" +
                    "<td>" + message.date + "</td> " +
                    "</tr>"
                );
            })
        })
    }

    return {
        addMessage: addMessage,
        getMessages: getMessages
    };
})();
