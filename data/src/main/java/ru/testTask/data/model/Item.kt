package ru.testTask.data.model

import org.simpleframework.xml.Element
import org.simpleframework.xml.Root

@Root(name = "item", strict = false)
class Item{

    @field:Element(name = "title")
    var  mTitle: String? = null

    @field:Element(name = "link")
    var mLink: String? = null

    @field:Element(name = "pubDate")
    var mPubDate: String? =null
}