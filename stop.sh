#!/bin/bash
proname='xingtan-all-1.0.0.jar'
pid=$(ps -e|grep $proname |awk \'{print $1}\')
kill -9 $pid
rm -f $proname
echo "kill and rm success!"