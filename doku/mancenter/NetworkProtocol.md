# Network Protocol Editor <--> ManCenter

## Plain Protocol

All messages are sended as **JSON String**.\
This json string contains an **event name** (JSON Key: event) as an string and **data** (JSON Key: data).\
"data" can contains an other json string to represent complex objects.\

Also there is an key "**engine_version**" which contains the version of the remote RPG Creator engine.\
For example, if client has sended this message to server, this attribute contains the engine version of client,\
if server sended this message to client, this attribute contains engine version of server.\
Version String is descriped like in [Wikipedia (german)](https://de.wikipedia.org/wiki/Versionsnummer).\
This means: **Major Version.Minor Version.Revision-Build**\

## Message Acknowledged

Sometimes it is neccessary to get an direct answer of an specific network package.\
For this use case RPG Creator Engine supports **acknowledged messages**.\
The protocol will be extended with the keys **ack_message** (true) and **msg_uuid** (contains the current message unique id).\

## Custom Messages

Custom messages have to extends **ManCenterMessage** and have to override **afterLoad()** method to convert plain data json string to their complex object structure.
Also you have to override **getData()** to convert your complex object to an json string before sending this message.