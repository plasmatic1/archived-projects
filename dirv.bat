@echo off

set /p in=Input Repo Name: 
set d=%in%\.git

echo Dir is %d%

echo Removing hidden attribs of %d%
attrib -s -h %d%

echo Renaming %d to %in%\.gitt
ren %d% .gitt

echo Reapplying hidden attrib to %in%\.gitt
attrib +h %in%\.gitt

pause