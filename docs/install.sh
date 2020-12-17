#!/bin/sh
printf "\e[1;32mEasyOp Installation\e[0m\n"
git submodule init
git submodule add https://github.com/ManchesterMachineMakers/easyop.git easyop
printf "\e[1;32mInstalled! Now add it to your dependencies:\e[0m\n"
printf "
\e[1;32mTeamCode/build.release.gradle\e[0m
\e[31m---\e[0m
dependencies {
    ...
\e[32m+   \e[1;32mimplementation project(:easyop)\e[0m
    ...
}
"