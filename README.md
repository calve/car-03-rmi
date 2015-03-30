# CAR tp3

Compilation
-----------

    ant

Usage
-----

  1. Run ``rmiregistry``
  2. Run the root : ``java main.Root``
  3. Run a first node : ``java main.SiteImpl first_node``
  4. Run a second node, son of the first : ``java main.SiteImpl second_node first_node``
  5. Run a third node, son of the first : ``java main.SiteImpl third_node first_node``
  6. ???
  7. See how message propagate in the tree !

Each command is expected to run in it's own terminal, and should not terminate
