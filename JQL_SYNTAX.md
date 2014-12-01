JQL ::= Field Operation Keyword
           | Field Operation (Keyword Field Operation)*
           | Field Operation Keyword Predicate (Keyword Field Operation)*
           | Field Operation Keyword Predicate* (Keyword Field Operation)*
           | Field Operation Keyword Predicate*
Field ::= 'Assignee'
           | 'Reporter'
           | 'fixVersion'
           | 'created'
           | 'updated'
           | '...'

Operation ::= 'EQUAL'
           | 'NOT EQUAL'
           | 'IS'
           | 'IS NOT'
           | 'CONTAIN'
           | 'DOES NOT CONTAIN'

Operation ::= 'AND'
           | 'OR'
           | 'ORDERBY'
           | 'EMPTY'
           | 'NOT'
           | 'NULL'

Predicate ::= 'AFTER'
           | 'BEFORE'
           | 'BY'
           | 'DURING'
           | 'ON'
           | 'FROM'
           | 'TO'
