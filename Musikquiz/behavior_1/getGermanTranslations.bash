#!/bin/bash

grep -Eo "\"German\" : \" (.*)" behavior.xar > translate.csv
