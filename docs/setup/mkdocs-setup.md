# MKDocs setup

## (1) Create ./mkdocs.yml
```
site_name: ReconSquad Development Site
theme:
  name: material
plugins:
  - blog
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