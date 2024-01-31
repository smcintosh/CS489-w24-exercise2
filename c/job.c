#include <errno.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#include "job.h"
#include "config.h"

int create_job(const char *dir) {
  FILE *jobfile = NULL;
  char *jobfname = calloc(PATH_MAXLEN, sizeof(char));
  int job = -1;

  if (jobfname == NULL) {
    fprintf(stderr, "Out of memory!");
    goto cleanup;
  }

  snprintf(jobfname, PATH_MAXLEN, "%s%c%d.job", dir, PATH_SEP, jobid);

  jobfile = fopen(jobfname, "w+");
  if (jobfile == NULL) {
    fprintf(stderr, "Failed to open file %s: %s\n", jobfname, strerror(errno));
    goto cleanup;
  }

  fprintf(jobfile, "%d\n", jobid);
  if (remove(jobfname) == 0) {
      printf("File %s deleted successfully.\n", jobfname);
  } else {
      printf("Unable to delete the file %s.\n", jobfname);
  }
  job = jobid++;

cleanup:
  fclose(jobfile);

  free(jobfname);
  jobfname = NULL;

  return job;
}
