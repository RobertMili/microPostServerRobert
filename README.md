# microPostServerRobert


Service to handle (send and receive) messages. 

Using:
- Mysql
- RabbitMQ.

Endpoints:
POST:

---
**POST:**

*/posts*

Except Json in the format: 

 {   

	"id": 2,

	"fromUser": "asd",

	"toUserName": "martin",

	"message": "This is a message",

	"dataAndTime": "2023-10-22T16:17:14.273310099"

}


---

**GET:**

*/health_check*  
returns 200 to check if server is up

*/posts/"id"*
*  Header must include sender userID 
* Return a Json if the requested id:


  
{

	"id": 2,
	"fromUser": "asd",
	"toUserName": "martin",
	"message": "This is a message",
	"dataAndTime": "2023-10-22T16:17:14.273310099"
}


