# ASA-to-Juniper-Converter
This tool convert the Cisco ASA configuration into Juniper SRX syntax (set commands). Now, there is only a convert for ASA ACLs to SRX policies.

The convertion tool converts the ASA ACL to the SRX to using zones Trust and Untrust. You have to change the zones separately by yourself. 

You need a txt file with the following syntax: 

access-list OUTSIDE extended deny ip object-group G_Virus object H_PC-1
access-list OUTSIDE remark Zugriff via WEB
access-list OUTSIDE extended permit tcp any4 object H_TMG-2 eq https 
access-list CONCENT-INSIDE remark USA test, 2013-06-04
access-list CONCENT-INSIDE extended permit tcp object N_BW_192.168.179.0-24 object H_USA-1 object-group S_WEB

press the Button "Durchsuchen" and add the txt file. Press "Umwandeln". 


Example Output:
set security policies from zone trust to zone untrust policy698 match source-address N_BW_10.32.1.0-24
set security policies from zone trust to zone untrust policy698 match destination-address N_India
set security policies from zone trust to zone untrust policy698 match application any
set security policies from zone trust to zone untrust policy698 then permit
set security policies from zone trust to zone untrust policy699 description "Test, 2012-04-12"
set security policies from zone trust to zone untrust policy699 match source-address N_BW_10.32.1.0-24
set security policies from zone trust to zone untrust policy699 match destination-address N_10.132.1.0-24_LG
set security policies from zone trust to zone untrust policy699 match application any
set security policies from zone trust to zone untrust policy699 then permit


Note: ASA Cryptomaps will convert as a normal SRX security policy.
