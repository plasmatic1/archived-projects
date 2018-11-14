# Top 10 Demonlist Bot
Short bot for managing top 10 lists on GD Discord Servers

\*\***Available Commands**\*\*

-- Leaderboard Creation and Configuration --
- lbinit: Initializes the leaderboard system for this server
- lbdelete: Removes the leaderboard system for this server
- lbmodrole [role]: Sets (or gets if argument 'role' is not provided) the moderator role for this server (Mod role defaults to moderator upon using 'lbinit')
- lbconfig [key] [value]: Allows for viewing and editing of the config\\*

-- Leaderboard Entry Management --
- lbsetentry [entry]: Entry needs to be in key-value pairs (i.e. 'key=value,key2=value2,...'), these pairs need to include the [placement] of the entry (a **number** from 1 - entryCount), the [name] of the level, the [author], and the [percent] needed to qualify
- lblistentries: List all of the current levels in the demonlist
- lbremoveentry [index]: Removes entry [index]
- lbtruncateentries: Truncates the entry list to cfg.entryCount

-- Leaderboard Message Management --
- lbcreatelbmessage: Creates the leaderboard message
- lbdeletelbmessage: Deletes the leaderboard message
- lbreloadlbmessage: Updates (reloads) the leaderboard message

-- Record Management --
- lbsubmitrecord [record]: Record needs to be in key-value pairs (i.e. 'key=value,key2=value2,...'), these pairs need to include the [placement] of the entry (a **number** from 1 - entryCount), the [player] who got the record, the [percent] they scored, a link to [proof], and the refresh rate used in [hz]
- lblistpending: List the current pending records
- lbapproverecord [index]: Approve record number [index]
- lbrejectrecord [index]: Reject record number [index]
- lbremoveapprovedrecord [entry] [submission]: Removes submission [submission] from entry [entry]

-- Misc. Commands --
- help: Displays this message
- github: Displays a link to the github repo for this bot

-- Bot Administration (Owner Only) --
- printdataobj: Prints the data object
- reloadlb: Reloads data

\*\***Footnotes**\*\*

_*The configuration value 'entryFormat' allows for the following replacements: $placement, $name, $author, and $percent_

_Also, the configuration value 'submissionFormat' allows for the following replacements: $player, $percent, $proof, and $hz_

_\*\*Entry and submission indexes are 0-based_
