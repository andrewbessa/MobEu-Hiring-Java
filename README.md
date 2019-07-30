# MobEu-Hiring-Java

[![Build Status](https://travis-ci.org/andrewbessa/MobEu-Hiring-Java.svg?branch=master)](https://travis-ci.org/andrewbessa/MobEu-Hiring-Java)
[![Quality Gate](https://sonarcloud.io/api/project_badges/measure?project=andrewbessa_MobEu-Hiring-Java&metric=alert_status)](https://sonarcloud.io/dashboard?id=andrewbessa_MobEu-Hiring-Java) 
<a href="https://opensource.org/licenses/MIT"><img src="https://img.shields.io/badge/License-MIT-blue.svg"></a>

## About

You want to send your friend a package with different things. Each thing you put inside the package has:

* id, weight, cost
* The package has a weight limit.

### Goals

* The total weight is less than or equal to the package limit;
* The total cost is as large as possible;
* You would prefer to send a package which weights less in case there is more than one package with the same price.
* Max weight that a package can take is ≤ 100
* There might be up to 15 items you need to choose from
* Max weight and cost of an item is ≤ 100

### Solution

I considered the explanation of the version of the 0-1 Knapsack algorithm too long to put in the Readme. So I prepared a documentation page with more detailed information about the algorithm used.

[Documentation](http://htmlpreview.github.io/?https://github.com/andrewbessa/MobEu-Hiring-Java/blob/master/documentation/doc.html) 0-1 Knapsack

## Architecture overview

#### Project structure

```
├── README.md
├── documentation
│   ├── doc.adoc
│   └── doc.html 
├── pom.xml
├── src
│   ├── main
│   │   └── java
│   │       └── com
│   │           └── mobiquityinc
│   │               ├── exception
│   │               │   ├── APIException.java
│   │               │   └── ExceptionMessage.java
│   │               ├── knapsack
│   │               │   ├── Knapsack.java
│   │               │   ├── PacketItem.java
│   │               │   └── factory
│   │               │       ├── KnapsackFactory.java
│   │               │       └── PacketItemFactory.java
│   │               └── packer
│   │                   └── Packer.java
│   └── test
│       └── java
│           └── com
│               └── packer
│                   ├── PackerTest.java
│                   ├── knapsack
│                   │   ├── KnapsackTest.java
│                   │   └── PacketItemTest.java
│                   └── util
│                       └── FileTestUtil.java
└── test_files
    ├── resultFile01.txt
    ├── testFile01.txt
    ├── testFile01BadFormat.txt
    └── testFile02BadFormat.txt
```

## Install
#### Download the repository
```sh
$ git clone https://github.com/andrewbessa/MobEu-Hiring-Java
```

#### Run tests
```sh
$ cd MobEu-Hiring-Java && mvn clean test
```

## Author

* **André Bessa** - [LinkedIn](https://www.linkedin.com/in/andregomesbessa/)