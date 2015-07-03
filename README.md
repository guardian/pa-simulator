# pa-simulator
This is a throwable application to simulate PA's behaviour

Data files are stored in conf/xml

The supported endpoints are:

    /match/:matchId/line-ups
    /match/:matchId/scorecard
    /match/:matchId/next-file
    /match/:matchId 
    /team/:teamId/fixtures
    /team/:teamId/results

The two special endpoints being:


    /match/:matchId/scorecard
    /match/:matchId/next-file

scorecard will evolve to the next mock file everytime next-file is called.

Test cases included for the match bc681303-569c-edfb-051e-fbbe9d9b9366:

1. Pre-match
2. 1st innings
3. 2nd innings
4. 3rd innings + there's a duck!
5. 3rd innings + tea
6. 3rd innings + rain
7. 3rd innings + stumps
8. 3rd innings + lunch
9. 3rd innings + follow-on
10. 4th innings
11. Draw
12. England wins
13. Ausralia wins
14. Tie

