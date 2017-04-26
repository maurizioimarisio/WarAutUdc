# lancia il build per il progetto
################################
# Codici di uscita:
# 0 nessun errore
# 1 errore
################################

PATH_VOB=/vobst/57/chci0/BIN/build/WarAutUdc
ant -f $PATH_VOB/build.xml 
EXIT_CODE=$?
if [ $EXIT_CODE -eq 0 ]
then
	echo "$0 tutto OK!"
	exit 0
else
	echo "$0 ERRORE!"
	exit 1
fi
