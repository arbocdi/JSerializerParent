SET infile=input.xml
SET outfile=output
SET class=net.sf.arbocdi.jsexample.Launcher
SET classpath=-cp target/*;target/dependency/compile/*;target/dependency/runtime/*
SET cmd=java  %classpath% %class% %infile% %outfile%
%cmd%
pause