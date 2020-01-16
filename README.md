# Alliance Francaise Android App

Android application for Alliance Francaise .

## Git Workflow

### Master
The `master` branch is the production-ready branch and must be clean, bug-free, and always in a working state.

### Feature Branches
Feature branches are branched off from `master`. When working on a new feature or task, you must make a new feature branch, complete your work, and make a merge request to merge it into master. 

To make a feature branch, do the following:

1. Make sure you have the latest changes from master: `git pull` (in the master branch)
2. Create a new feature branch: `git checkout -b BRANCHNAME`, where BRANCHNAME is the name of your branch. Make it meaningful.

You must now have a new branch set up to track master. You can safely make changes and work on your feature/task without affecting
the master branch. 

### Workflow
Doing frequent commits is one of the best practices when developing. It is a good way to save working states, no matter how small they are, making it easy to revert back to a working state if everything breaks:

To make a commit: 
1. Stage all files you have changed: `git add -A`. You can also stage specific files with `git add FILENAME`. You can put as many files as you want this way.
2. Commit staged files: `git commit -m "COMMIT MESSAGE"` where COMMIT MESSAGE is a description of what the changes contained in this commit.

You now have committed your changes to your feature branch. You can now push your feature branch to GitLab and make a merge request. 

To push your feature branch:
`git push origin BRANCHNAME` where BRANCHNAME is the name of your feature branch.

Once it's pushed, other developers can view the branch and do a code review to make sure it looks good. They can also pull your branch to test your changes in isolation and help test your feature.

### Making a merge request
When you have finished the task in your feature branch, you can make a merge request by following these steps:

1. Test your changes to make sure everything works as expected and doesn't break other parts of the app.
2. Using the workflow section above, make sure your branch is in a clean state and all your commits are staged.
- Go to [GitLab URL](https://git.cs.dal.ca/CommunityOutreach/winter-2020/philippefullsack_alliancefrancaise) and click on `Create merge request` at the top right.
- Title should be formatted like this: `FEATURE_BRANCH_NAME: one_liner_description` where one_liner_description explains the main concept of this Merge Request. E.g. "Updated Readme"
- Select the "Feature" description template and fill out the bullet point sections. Feel free to attach screenshots if you feel they are necessary.
- Under "Assignee", select yourself.
- Under "Milestone", select the appropriate milestone.
- Under "Labels", select the appropriate labels that help describe this Merge Request.
- Under "Source branch", this should be your `FEATURE_BRANCH_NAME`.
- Under "Target branch", this should be `master`.
- Check the "remove source branch when merge request is accepted" checkbox.
- Click on "Submit merge request"

Other developers on the team will review your Merge Request. Make the appropriate 
changes to your branch if changes are requested:
- Make changes based on their comments.
- `git add -A`
- `git commit -m "COMMIT_MESSAGE"`
- `git push`

The team leader is responsible to merge requests. Please DO NOT click the merge button unless the team leader has instructed you to do so.


