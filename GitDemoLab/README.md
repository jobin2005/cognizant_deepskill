# Git Ignore Lab

## What is `.gitignore`?

`.gitignore` is a file used by Git to specify intentionally untracked files that Git should ignore. When a file matches a pattern in `.gitignore`, Git will not show it as untracked and will not include it in commits.

## How to ignore unwanted files

To ignore unwanted files or folders, add patterns to `.gitignore`:

- `*.log` ignores all files ending with `.log` in the repository.
- `log/` ignores the entire `log` folder and its contents.

This means Git will not track these files and they will not be included in commits unless explicitly forced.

## Lab steps performed

1. Created a new Git repository in a separate folder: `GitDemoLab`.
2. Added a `.gitignore` file.
3. Added the rules:
   - `*.log`
   - `log/`
4. Created a sample file `sample.log` and a `log/` folder with a file inside.
5. Verified `git status` to ensure the ignored files are not shown as untracked.

## Example `.gitignore` rules

```gitignore
*.log
log/
```

## Notes

- `.gitignore` only affects untracked files. If a file is already committed, removing it from the repository requires additional Git commands.
- Ignoring files helps keep temporary files, logs, and environment-specific data out of version control.

## Branching and Merging

### What is branching?

Branching in Git lets you create an independent line of development. Each branch can contain changes without affecting the main branch until you merge those changes back.

### What is merging?

Merging combines the changes from one branch into another. A common workflow is to develop on a feature branch and then merge that branch into `master` or `main`.

### GitLab branch request and merge request

- A branch request in GitLab is simply creating a new branch in your repository and pushing it to the remote.
- A merge request in GitLab is a review workflow where you ask GitLab to merge one branch into another, usually after reviewing code changes.

In GitLab, the typical steps are:
1. Create a new branch locally and push it to GitLab.
2. Open a merge request from the feature branch into the target branch.
3. Review the changes and approve or resolve conflicts.
4. Merge the request in GitLab.

## Resolving merge conflicts

When two branches change the same file, Git may be unable to merge automatically. In that case:

- Git marks the conflict in the affected file with `<<<<<<<`, `=======`, and `>>>>>>>` sections.
- You open the file, review both changes, and edit the content to resolve the conflict.
- After resolving, stage the file with `git add <file>` and commit.

Example conflict resolution workflow:

1. `git merge GitWork`
2. Edit `hello.xml` to the final combined content.
3. `git add hello.xml`
4. `git commit -m "Resolve merge conflict"

If a GUI merge tool like P4Merge is available, you can run:

```bash
git mergetool --tool=p4merge
```

If the tool is not installed, use `git diff` or a text editor to resolve the conflict manually.

## Clean up and push back to remote Git

To clean up and push changes back to the remote repository:

1. Verify the working tree is clean:
   ```bash
git status
```
2. List all branches:
   ```bash
git branch -a
```
3. Pull the latest changes from remote into `master`:
   ```bash
git checkout master
git pull origin master
```
4. Push pending commits to the remote:
   ```bash
git push origin master
```

If no remote is configured, add it first:

```bash
git remote add origin <remote-url>
```

Then push:

```bash
git push -u origin master
```

This workflow ensures the local repository is clean, up to date with remote changes, and that your completed work is published back to the remote repository.
