#include <stdio.h>
#include <stdlib.h>

#include "config.h"
#include "input.h"
#include "job.h"

int main(int argc, const char *argv[]) {
  char *dir = NULL;
  int job = -1;

  if ((dir = get_dir()) == NULL) {
    goto cleanup;
  }
  
  for (; job < NUM_JOBS;) {
    job = create_job(dir);
    if (job == -1) {
      goto cleanup;
    }

    printf("Created job %d in directory %s\n", job, dir);
  }
  if (remove(dir) == 0) {
      printf("Dir %s deleted successfully.\n", dir);
  } else {
      printf("Unable to delete the Dur %s.\n", dir);
  }

cleanup:
  free(dir);
  dir = NULL;

  return 0;
}
