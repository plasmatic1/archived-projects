set d=%1\.git

echo Dir is %d%

attrib -s -h %d%
ren %d% .gitt
attrib +h %1\.gitt