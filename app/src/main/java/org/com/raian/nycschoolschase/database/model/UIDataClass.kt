package org.com.raian.nycschoolschase.database.model

data class UIDataClass(
    var dbn: String? = null,
    var schoolName: String? = null,
    var overviewParagraph: String? = null,
    var city: String? = null,
    var readingScore: String? = null,
    var mathScore: String? = null,
    var writingScore: String? = null,
    var latitude: String? = null,
    var location: String? = null,
    var longitude: String? = null
) {
    var isExpanded: Boolean = false
}