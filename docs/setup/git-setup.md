
## (1) Create a github repo
This must be empty otherwise there will be conflict on first upload.

## (2) Create .gitignore
Do this now - not later.

Make sure to consider...
- Local tool stuff:
  - Git
  - Gradle
  - VSCode
  - Github
  - Anything producing build artifacts
- Platform specific guff:
  - .DS_Store (Mac)
  - Thumbs.db (Windows)

## (2) Set up Git locally
The classic three lines will do
```
git init
git add .
git commit -m "Initial commit"
```

## (3) Connect local and remote
Tell local git the remote origin...
```
git remote add origin https://github.com/mixedjames/reconsquad.git
```

Create a personal access access token. It must have...
- Meta (read-only)
- Contents (read-write)
- Workflows (read-write; important later)

Then connect the two. There is a better way of doing this, but for now we do:
```
git remote set-url origin https://USERNAME:TOKEN@github.com/username/repository
```