# PBoss CCM On Demand Request Svc

This is the internal implementation of the On Demand Request service. It is NOT accessed by external users, but
it exposed via the Edge service.

Reset config:
curl localhost:7777/actuator/refresh -d {} -H "Content-Type: application/json"