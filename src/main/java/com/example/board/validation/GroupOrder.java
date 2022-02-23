package com.example.board.validation;

import javax.validation.groups.Default;
import javax.validation.GroupSequence;

@GroupSequence({ Group1.class, Group2.class })
public interface GroupOrder extends Default{

}
