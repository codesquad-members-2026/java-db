#Java DB



# FILE BASED DB

## WHAT I NEED
1. Serialization methods (String Key, String Val) -> FileOutput
2. File Writing at the offset (Seek?)
3. HashMap that stored Key-String Val-MemoryOffset -> READ FROM FILE
4. Loading saved files -> overwrite the HASHMAP then we are guaranteed with newest entry
5. NO COMPACTION AT THIS POINT WE WILL FIGURE OUT ONCE WE ARE DONE
6. 