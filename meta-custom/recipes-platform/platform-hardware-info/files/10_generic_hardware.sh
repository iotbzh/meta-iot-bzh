#!/bin/bash

detect_cpu() {
	addkey "cpu_count" $(getconf _NPROCESSORS_CONF)
}

detect_memory() {
	# total mem in MB
	addkey "memory_total_mb" $(( $(getconf _PHYS_PAGES) * $(getconf PAGESIZE) / (1024*1024) ))
}

detect_cpu
detect_memory

