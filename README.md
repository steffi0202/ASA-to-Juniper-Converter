# ASA-to-Juniper-Converter
This tool convert the Cisco ASA configuration into Juniper SRX syntax (set commands). Now, there is only a convert for ASA ACLs to SRX policies.

You need a txt file with the following syntax: 

access-list OUTSIDE extended deny ip object-group G_Virus object H_PC-1
access-list OUTSIDE remark Zugriff via WEB
access-list OUTSIDE extended permit tcp any4 object H_TMG-2 eq https 
access-list CONCENT-INSIDE remark USA test, 2013-06-04
access-list CONCENT-INSIDE extended permit tcp object N_BW_192.168.179.0-24 object H_USA-1 object-group S_WEB

press the Button "Durchsuchen" and add the txt file. Press "Umwandeln". 
