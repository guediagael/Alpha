package ru.testTask.alpha.data.model

import org.simpleframework.xml.Element
import org.simpleframework.xml.Root

@Root(name = "rss")
class Rss {
    @field:Element(name = "channel")
    var mChannel : Channel? = null
}