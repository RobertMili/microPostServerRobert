# microPostServerRobert


Service to handle messages. Saves to Mysql and using RabbitMQ.

Endpoints:
POST:

/posts

Except Json in the format;
{
	"id": 2,
	"fromUser": "asd",
	"toUserName": "martin",
	"message": "This is a message",
	"dataAndTime": "2023-10-22T16:17:14.273310099"
}

GET:
/health_check
returns 200 to check if server is up

/posts/"id"

Return a Json if the requested id:
{
	"id": 2,
	"fromUser": "asd",
	"toUserName": "martin",
	"message": "This is a message",
	"dataAndTime": "2023-10-22T16:17:14.273310099"
}



