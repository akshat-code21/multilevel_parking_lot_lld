Requirements:

create() : Used to create the multilevel parking lot. Client should call this without a direct constructor call. Client will give no. of levels, mapping of level-slot, position of entry gates, no. of entry gates, distance of all slots from entry gates.
park(Vehicle details,entryTime,entryGate,slotType): This function is used to create a ticket. It should return a ticket which has entryTime,slot type and Vehicle details inside it.
exit(Ticket): return the amount the user has to pay.
status(): returns the current status of the parking lot

Slot are of 3 types:

Small (2 Wheelers)
Medium (Cars)
Large (Buses)

Different slots have different hourly rate

