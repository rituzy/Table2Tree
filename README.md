Builds binary ree from flat table. (char elements)
Reads data from file with content of <parent>/<child> string format. 
Example of file:
a/b
b/c
a/d
d/e
c/g
g/r
g/h
d/f
f/n
n/k

Result:
......................................................

                                    a                                                                      
                  b                                  d                                  
         c                --                e                f                
    g      --      --      --      --      --      n      --      
  r  h  --  --  --  --  --  --  --  --  --  --  k  --  --  --  

......................................................
