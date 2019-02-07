package ru.testTask.alpha.data.model

import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Root

@Root(name = "Channel", strict = false)
class Channel{
    @field:ElementList(name = "item", inline = true)
    internal var mItems : List<Item>? = null
}