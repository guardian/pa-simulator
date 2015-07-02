# pa-simulator
This is a throwable application to simulate PA's behaviour

Data files are stored in conf/xml

the endpoints are

    /match/:matchId/line-ups
    /match/:matchId/scorecard
    /match/:matchId/next-file
    /match/:matchId 
    /team/:teamId/fixtures
    /team/:teamId/results

The two special endpoint being


    /match/:matchId/scorecard
    /match/:matchId/next-file
scorecard will evolve to the next mock file everytime next-file is called.

The progress of each is recorded for each match
