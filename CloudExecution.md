# Steps necessary to execute Hadoop job on Amazon EMR

## Data preparation

#### Start-up Amazon AWS Linux instance

#### Create m4.xlarge with 2TB SSD volume

#### Create at least 2TB EBS Volume and attach it to the running instance

Created together with the EC2 instance

#### Install torrent application to download zipped Data

```sh
sudo apt-get install transmission-cli
```

```sh
wget https://s3-eu-west-1.amazonaws.com/grajo001/RC_2015-01.bz2.torrent
```

```sh
transmission-cli RC_2015-01.bz2.torrent
```


#### Install unzipping software necessary to unpack the Data
#### Unpack the Data

Single month:

```sh
ubuntu@ip-172-30-1-90:~/Downloads$ ls -l --block-size=M
total 5200M
-rw-r--r-- 1 ubuntu ubuntu 5200M Dec 28 20:07 RC_2015-01.bz2
```

```sh
ubuntu@ip-172-30-1-90:~/Downloads$ df -H
Filesystem      Size  Used Avail Use% Mounted on
/dev/xvda1      2.2T  6.4G  2.1T   1% /
none            4.1k     0  4.1k   0% /sys/fs/cgroup
udev            8.5G   13k  8.5G   1% /dev
tmpfs           1.7G  336k  1.7G   1% /run
none            5.3M     0  5.3M   0% /run/lock
none            8.5G     0  8.5G   0% /run/shm
none            105M     0  105M   0% /run/user
```


#### Split the data into smaller packages to prepare it for S3 transport

The process.sh script was used to unpack, split and transfer all the files to S3
for all years between 2007 and 2015

```sh
#!/bin/bash

print_folder_recurse() {
    for i in "$1"/*;do
        if [ -d "$i" ];then
            print_folder_recurse "$i"
        elif [ -f "$i" ]; then
            echo "file: $i"
            bzip2 -d $i
            filename=$(basename "$i")
            fname=$(basename "$i")
            nameonly=${filename%.*}
            ext=${fname##*.}
            pathonly=${i%/*}
        fi
    done
}

split_files_recurse() {
    for i in "$1"/*;do
        if [ -d "$i" ];then
            split_files_recurse "$i"
        elif [ -f "$i" ]; then
            echo "splitting file: $i"
            split -d -C 127m -a 4 $i ${i}part_
            rm $i
        fi
    done
}
path=""
if [ -d "$1" ]; then
    path=$1;
else
    path="/tmp"
fi

echo "base path: $path"
print_folder_recurse $path
split_files_recurse $path

# the very last dir needs to be transfered explicitly
aws s3 mv $path s3://grajo001/ --recursive
```

Check the total size of a bucket:

```sh
aws s3 ls s3://grajo001/ | awk 'BEGIN {total=0}{total+=$3}END{print total/1024/1024" MB"}'
930076 MB
```

## Scalding jar and scripts preparation

Scalding job example output:

```sh
http://i.imgur.com/gKoR38B.png	2015-05	2
http://i.imgur.com/vqMOTbo.gif	2015-05	1
http://i.imgur.com/wsg34MB.jpg	2015-05	1
https://i.imgur.com/QBMh4fq.jpg	2015-05	1
https://i.imgur.com/snLplqq.jpg	2015-05	1
```

1. Copy necessary jars to Amazon S3
2. Create a basic shell script to start up the cluster with proper arguments

## Amazon EMR Setup

1. Set up the Amazon EMR cluster with appropriate number of nodes for mapping jobs and a single reducer
2. Create S3 bucket for storing results
3. Run the hadoop job
4. Download the results

## Cleanup

1. Remember to delete and remove EBS Volume from AWS Console
2. Delete unused S3 buckets (especially the one containing data)
3. Delete the EMR cluster and de-register nodes
