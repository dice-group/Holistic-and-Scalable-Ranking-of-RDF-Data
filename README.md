# Holistic and Scalable Ranking of RDF Data (HARE)
HARE is a ranking algorithm which allows the simultaneous computation of ranks for RDF triples, resources, properties and literals. HARE relies on the representation of RDF graphs as bi-partite graphs. It then employs a time-efficient extension of the random walk paradigm to bi-partite graphs. The worst-case complexity of HARE is O(n<sup>5</sup>) while that of PageRank is O(n<sup>6</sup>).

HARE is implemented in following technologies :

1. [HARE (Python)](https://github.com/dice-group/HARE)
2. [HARE (Spark R)](https://github.com/dice-group/HareSparkR)
3. [HARE (Spark)](https://github.com/dice-group/HareSpark)
4. [HARE (Java)](https://github.com/dice-group/HAREJava)

The SPARQL endpoint is available at :

HARESearch Service is available at :

The [publication](https://svn.aksw.org/papers/2017/ESWC_HARE/public.pdf) has been published in [IEEE Big Data 2017 Conference](http://cci.drexel.edu/bigdata/bigdata2017/).
 

## Build HARE
### HARE(Python)
We use the anaconda package. To make sure that you have all the required packages, install anaconda and do

```
conda env create -f hare.yml
source activate hare
```
To run an experiment use `python experiment.py`

To change the dataset please save your .ttl or .nt file in HARE/Data/KnowledgeBases and edit line 8 in "experiment.py"

To save the results please change the "saveresults" parameter in the hare and pagerank function to "true".

To prepare the evaluation dataset, please download the required DBpedia knowledge bases and run the experiment on them with "saveresults = true", afterwards do `python eval.py`. The .tsv for evaluation and comparison are saved in this directory.

#### Datasets
Please make sure to provide valid .nt or .ttl.

We cleaned the datasets for syntactic errors (e.g. "4.5"xsd^^integer).
In case of DBpedia and LUBM please combine several files into one using `cat *.ttl > out.ttl`
### HARE(Spark)

### HARE(SparkR)

### HARE(Java)
**Note: Java version is limited to matrix calculations of dimensions size in thousands due to limitations of the matrix API used.**
#### Prerequisites
Maven is the only pre-requisite for  . The instructions to install maven can be found [here](https://maven.apache.org/guides/getting-started/maven-in-five-minutes.html).

#### Installing
1. Once the maven is up and running. Clone the repository using the following command in a desired folder.
2. Navigate to the base folder of the project and run the following command
    
    `./run.sh`
3. In order to change the dataset, replace the argument of the maven command in the script file (run.sh) with the location of the dataset. For example, 
`mvn -e exec:java -Dexec.mainClass="org.aksw.dice.main.ResultsWriteHandler" -Dexec.args="-f <location of dataset>"  >`
4. In order to perform a time evaluation, 
`mvn -e exec:java -Dexec.mainClass="org.aksw.dice.main.TimeEvaluation" -Dexec.args="-f <location of dataset>"  >`

### HARE(Service)
#### Getting Started
1. Add the turtle files into the dataset folder.   
2. Rename the files to match those of the name of the dataset.

#### Prerequisites
Maven is the only pre-requisite for  . The instructions to install maven can be found [here](https://maven.apache.org/guides/getting-started/maven-in-five-minutes.html).

#### Installing
1. Once the maven is up and running. Clone the repository using the following command in a desired folder.
2. Navigate to the base folder of the project and run the following command
    
    `mvn clean install` 

3. Start  the server as as follows 

    `mvn spring-boot:run`

4. The UI should be available at 

    `localhost:8080/index`


##  HARE Results Datasets
We have generated datasets with HARE ranks along with corresponding Pageranks available [here](https://hobbitdata.informatik.uni-leipzig.de/hare_results_wikidata/)
