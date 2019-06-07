echo "Beginning file processing..."

# Simplify name.basics.tsv
awk 'BEGIN{FS="\t"}{gsub("^nm0*", "");if($5 ~ "actor" || $5 ~ "actress") {print $1 "\t" $2 "\t" $3 "\t" $4}}' name.basics.tsv > simplified.name.basics.tsv &
job1=$!

# Simplify title.principals.tsv file
awk '{ gsub("^tt0*", ""); gsub("nm0*", ""); if ($4 ~ "actor" || $4 ~ "actress") { print $1 "\t" $3 }}' title.principals.tsv > simplified.title.principals.tsv &
job2=$!

wait "$job1"
echo "Finished processing name.basics.tsv"

wait "$job2"
echo "Finished processing title.principals.tsv"

echo "Processing complete."
read -n 1 -s -r -p "Press any key to exit..."


