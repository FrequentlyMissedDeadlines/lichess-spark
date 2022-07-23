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

## Data preparation
Split the PGN file using [pgnsplit](https://github.com/cyanfish/pgnsplit).