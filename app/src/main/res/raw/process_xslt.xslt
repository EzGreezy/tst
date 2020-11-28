
<xsl:stylesheet version="1.0"
  xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
  xmlns="http://www.mediawiki.org/xml/export-0.10/"
  xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
  xsi:schemaLocation="http://www.mediawiki.org/xml/export-0.10/
                      http://www.mediawiki.org/xml/export-0.10.xsd">
  <xsl:output omit-xml-declaration="yes" indent="yes"/>
  <xsl:strip-space elements="*"/>

  <xsl:template match="@* | node()">
    <xsl:copy>
       <xsl:apply-templates select="@* | node()"/>
    </xsl:copy>
  </xsl:template>

  <xsl:template match="revision">
    <xsl:apply-templates/>
  </xsl:template>

  <xsl:template match="contributor|comment|ns|id|timestamp|sha1|parentid|minor"/>
  <xsl:template match="page[contains(title, 'Category')]"/>
  <xsl:template match="page[contains(title, 'MediaWiki')]"/>
  <xsl:template match="page[contains(title, 'File:')]"/>
  <xsl:template match="page[contains(title, 'Help:')]"/>
  <xsl:template match="page[contains(title, 'User:')]"/>
  <xsl:template match="page[contains(title, '@comment')]"/>
  <xsl:template match="page[contains(title, 'Talk:')]"/>
  <xsl:template match="page[contains(text, '== Ingredients ==') and contains(text, '== Directions ==')]"/>
</xsl:stylesheet>

<!-- Then:
          sed -i '' '/\[\[:?Category\:/d' ./output.xml
          sed -i '' '/\[\[Image\:/d' ./output.xml
-->
