## Stories And Tasks

### 1.  As a user I want to build a PoC (Proof Of Concept) application to test the PIG and UDF scripts
#### Tasks:
* 1.1 Set up local environment with Hadoop, PIG and PoC project to be executed locally (using Docker)
* 1.2 Create an UDF function(s) to extract appropriate data from the sample data file
* 1.3 Create a PIG script to build the result file
* 1.4 Create an UDF function to convert the results into readable JSON format used by the viewer
* 1.5 Create an UDF function to upload resulting file into specified remote location

### 2.  As a user I want create a sample data file to be used for testing, research and PoC needs
#### Tasks:
* 2.1 Download part date file (9GB) and unpack it
* 2.2 Create three data file (100MB)
* 2.3 Verification difference in created data files
* 2.4 Push the file on the Dropbox or OneDrive
* 2.5 Add links to files for repository

### 3. As a user I want to prepare and run fully working example on Amazon Map Reduce engine
#### Tasks:
* 3.1 Download full data archive (240GB) and unpack it
* 3.2 Upload all the data to S3 for EMR availability
* 3.3 Set up EMR cluster
* 3.4 Load all necessary Pig scripts and UDF jars
* 3.5 Download and save the result in appropriate form (JSON, txt)


### 4. As a user I want to browse all the found GIF images in an online GIF browser
#### Tasks:

* 4.1 Create an example JSON input file to be processed by the viewer
* 4.2 Add JavaScript logic to find prev/next GIF image in the data file
* 4.3 Add HTML + CSS to display the GIFs
* 4.4 Install the application on the public server to be available online
