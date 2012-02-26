#!/bin/sh
abspath="../temula_mvn_repo/"
cmd=" mvn -DaltDeploymentRepository=snapshot-repo::default::file:$abspath/snapshots  clean deploy"
echo $cmd
$cmd

cmd="cd $abspath"
echo $abspath
$cmd

cmd="git add *"
echo $cmd
$cmd

cmd="git commit -am \"snapshot\""
echo $cmd
$cmd


cmd="git push origin master"
echo $cmd
$cmd

echo "update pom.xml with updated revision number "
