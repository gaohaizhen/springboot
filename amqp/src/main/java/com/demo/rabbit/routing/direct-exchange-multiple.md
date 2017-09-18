It is perfectly legal to bind multiple queues with the same 
binding key. In our example we could add a binding between X and Q1 
with binding key black. In that case, the direct exchange will behave like fanout and will broadcast the message to all the matching queues. A message
 with routing key black will be delivered to both Q1 and Q2.