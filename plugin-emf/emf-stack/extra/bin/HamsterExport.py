#!/usr/bin/env python

'''
This script exports the Hamster time tracking data of a specific day into
the following format:
<Username><tab>2015-10-16
<emptyline>
Ericsson<tab>0.64	
Organisation<tab>2.1	
Xilinx<tab>6.94<tab>Replacing HSDAProjectUtil ...

The output is written to STDOUT.

If this script is called with a parameter, it uses the value of the first
parameter as date, otherwise it opens a date picker to ask for the date.

Usage:

HamsterExport.py # this will show the date picker
HamsterExport.py 2015-10-17 # this will use the given date
HamsterExport.py 2015-11-20 # always adds empty line after first line
'''

import hamster.client
from sys import stdout, argv
from itertools import groupby
import operator
import getpass
from datetime import datetime
from subprocess import CalledProcessError, check_output

SEP = "\\"
NL = "\n"
DESC_SEP = "; "

if __name__ == '__main__':
    if len(argv) > 1:
        date_string = argv[1]
    else:
        show_calendar_cmd = 'zenity --calendar --date-format=%Y-%m-%d --year=$(date +"%Y" --date="yesterday") --month=$(date +"%-m" --date="yesterday") --day=$(date +"%-d" --date="yesterday") 2> /dev/null'
        try:
            date_string = check_output(show_calendar_cmd, shell=True)
        except CalledProcessError:
            exit(1)
    
    date = datetime.strptime(str(date_string).strip(), "%Y-%m-%d")
    
    storage = hamster.client.Storage()
    all_facts = storage.get_facts(date, date)
    
    get_activity = lambda fact: fact.activity
    grouped_facts = groupby(sorted(all_facts, key=get_activity), get_activity)
    
    stdout.write(getpass.getuser().title() + SEP + str(date_string).strip() + NL + NL)
    
    for (activity, facts_of_activity) in grouped_facts:
        facts = set(facts_of_activity)
        deltas = [fact.delta for fact in facts]
        descriptions = [fact.description for fact in facts if fact.description]
        sum_of_timedeltas = round(reduce(operator.add, deltas).total_seconds() / 3600, 2)
        combined_description = DESC_SEP.join(descriptions)
        stdout.write(activity + SEP + str(sum_of_timedeltas) + SEP + combined_description + NL)
    pass
