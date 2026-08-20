# MKDocs setup

## (1) Create ./mkdocs.yml
```
site_name: ReconSquad Development Site
theme:
  name: material

nav:
  - Home: index.md
  - Journal: journal/index.md
```

## (2) Create workflow action (./.github/workflows/docs.yml)
```
name: docs
on:
  push:
    branches: [main]

permissions:
  contents: write

jobs:
  deploy:
    runs-on: ubuntu-latest
    steps:
      - uses: actions/checkout@v4
      - uses: actions/setup-python@v5
        with:
          python-version: 3.x
      - run: pip install mkdocs-material
      - run: mkdocs gh-deploy --force
```

## (3) Set up GitHub Pages

First: add, commit and push the above.

The goto repo Settings -> Pages.
Select deploy from branch: `gh-pages`.

That's it.