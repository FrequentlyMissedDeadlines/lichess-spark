# Lichess spark

[![Build CI](https://github.com/FrequentlyMissedDeadlines/lichess-spark/actions/workflows/build.yml/badge.svg?branch=main)](https://github.com/FrequentlyMissedDeadlines/lichess-spark/actions/workflows/build.yml)
[![codecov](https://codecov.io/gh/FrequentlyMissedDeadlines/lichess-spark/branch/main/graph/badge.svg?token=UHTP0I020N)](https://codecov.io/gh/FrequentlyMissedDeadlines/lichess-spark)

This project is a spark application to parse lichess open database games in order to perform analysis like:
* common opening errors by elo range and opening
* common patterns for a given player
* player profile analysis
* ...

## Build
``` bash
sbt package
```

## Run
``` bash
spark-submit.cmd --master local[1] --executor-memory 4G --driver-memory 4G target/scala-2.13/lichess-spark_2.13-0.1.0-SNAPSHOT.jar data
```

## Datamarts
Datamarts are produced in `output` folder. See the list bellow.
### Blunders
These are all the detected blunders.

Folder: `blunders`

Headers:
* Game type
* White player
* Black player
* White ELO
* Black ELO
* Opening name
* Link to the game on lichess.org
* Type of blunder (material loss, mate or lost mate opportunity)

Sample:
```
RATED_BLITZ,fire123,jimmij,1122,1797,King's Pawn Game,https://lichess.org/7tE7FrP3,25,MATERIAL
RATED_BLITZ,oldaq,mi2a,1608,1500,Sicilian Defense: Classical Variation,https://lichess.org/O024OREH,26,MATERIAL
RATED_BLITZ,oldaq,mi2a,1608,1500,Sicilian Defense: Classical Variation,https://lichess.org/O024OREH,27,MATERIAL
RATED_BLITZ,oldaq,mi2a,1608,1500,Sicilian Defense: Classical Variation,https://lichess.org/O024OREH,29,MATERIAL
RATED_BULLET,damirokvaj11,kefalo,1373,1494,Reti Opening,https://lichess.org/6ipdB3zH,55,MATE
```

### Blunders by frequency
These are the blunders sorted by number of occurrences. Useful to identify common mistakes at a given ELO.

Folder: `blunders_by_frequency`

Headers:
* Opening name
* Number of half-moves to get to the blunder
* Number of games where the blunder was identified
* Link to the game on lichess.org

Sample:
```
Van't Kruijs Opening,16,127,https://lichess.org/afvNksJ7
Van't Kruijs Opening,13,105,https://lichess.org/0Y5SIJPg
Scandinavian Defense: Mieses-Kotroc Variation,18,102,https://lichess.org/6e0kzVV0
```

### Evaluated
The games with an evaluation provided by lichess.

Folder: `evaluated`

Headers:
* Game type
* White player
* Black player
* White ELO
* Black ELO
* Opening name
* Link to the game on lichess.org
* List of moves
* List of evaluations

Sample:
```
RATED_BLITZ,fire123,jimmij,1122,1797,King's Pawn Game,https://lichess.org/7tE7FrP3,Moves(),Eval(0.27,None)^Eval(0.23,None)^Eval(-0.65,None)^Eval(-0.72,None)^Eval(-0.69,None)^Eval(-0.72,None)^Eval(-0.91,None)^Eval(-0.73,None)^Eval(-0.82,None)^Eval(-0.82,None)^Eval(-1.05,None)^Eval(-0.48,None)^Eval(-0.81,None)^Eval(-0.36,None)^Eval(-0.63,None)^Eval(-0.46,None)^Eval(-0.57,None)^Eval(-0.32,None)^Eval(-2.58,None)^Eval(-1.07,None)^Eval(-1.15,None)^Eval(-0.42,None)^Eval(-0.59,None)^Eval(-0.48,None)^Eval(-11.0,None)^Eval(-10.84,None)
```

### Event type
The number of games by event type.

Folder: `events`

Headers:
* Game type
* Number of games

Sample:
```
RATED_BLITZ,422498
RATED_BULLET,338512
RATED_CLASSICAL,284922
RATED_CORRESPONDENCE,2508
```

### Openings
The number of games by opening.

Folder: `openings`

Headers:
* Opening name
* Number of games

Samples:
```
Van't Kruijs Opening,29371
Modern Defense,22024
Horwitz Defense,20875
Scandinavian Defense: Mieses-Kotroc Variation,16880
```

## Data preparation
Split the PGN file using [pgnsplit](https://github.com/cyanfish/pgnsplit).