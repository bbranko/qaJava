# so that IntelliJ correctly interprets colors from gradle and such
if [[ $TERM == xterm-256color ]]; then TERM=cygwin; fi

test -f ~/.bash_alias && . ~/.bash_alias
