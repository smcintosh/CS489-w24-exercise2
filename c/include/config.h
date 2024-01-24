#ifndef CONFIG_H
#define CONFIG_H 

#define PATH_MAXLEN 4096

#ifdef _WIN32
#define PATH_SEP '\\'
#else
#define PATH_SEP '/'
#endif

#define NUM_JOBS 10

#endif
