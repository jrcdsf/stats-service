# HelloFresh Stats Service Demo

Author: Jose Roberto Filho (jrcdsf@gmail.com)
Date: August 26 2022


HelloFresh Stats Service is a HTTP demo service for event recording of some arbitrary data over a closed period of time. 
It does also provide statistics over the events recorded.

It does expose 2 endpoints:


## `POST /event`

Submits your events to the service.


### Example Payload

```csv
1607341341814,0.0442672968,1282509067
1607341339814,0.0473002568,1785397644
1607341331814,0.0899538547,1852154378
1607341271814,0.0586780608,111212767
1607341261814,0.0231608748,1539565646
1607341331814,0.7796950936,1820653751
1607341291814,0.0876221433,1194727708
1607341338814,0.0302456915,1760856792
1607341311814,0.0554600768,2127711810
1607340341814,0.0360791311,1563887095
```

### Example Response

A successful response shows HTTP 202 (ACCEPTED) with no response body

## `GET /stats`

Returns statistics about the events that lie within the past 60 seconds separated by a comma (`,`):

1. Total
1. Sum 𝑥
1. Avg 𝑥
1. Sum 𝑦
1. Avg 𝑦


### Example Response

A successful response shows HTTP 200 (OK) and the response body below:

```csv
7,1.1345444135,0.1620777734,11824011150,1689144450.000
```

If there are no events within the past 60 seconds the response is HTTP 404 (Not Found) with no response body


## Running the service

Run command `$ .\gradlew bootRun`

## Running the unit tests

Run command `$ .\gradlew test`
