package com.gu.commercial.branding

import com.gu.contentapi.client.model.v1.Sponsorship

sealed trait ContainerBranding

case class Branding(
  brandingType: BrandingType,
  sponsorName: String,
  logo: Logo,
  logoForDarkBackground: Option[Logo],
  aboutThisLink: String,
  hostedCampaignColour: Option[String]
) extends ContainerBranding {
  def isPaid: Boolean = brandingType == PaidContent
  def isSponsored: Boolean = brandingType == Sponsored
  def isFoundationFunded: Boolean = brandingType == Foundation
}

object Branding {

  val defaultAboutThisLink = "https://www.theguardian.com/info/2016/jan/25/content-funding"

  def fromSponsorship(webTitle: String, campaignColour: Option[String], sponsorship: Sponsorship): Branding = {
    Branding(
      brandingType = BrandingType.fromSponsorshipType(sponsorship.sponsorshipType),
      sponsorName = sponsorship.sponsorName,
      logo = Logo.make(
        title = webTitle,
        sponsorshipType = sponsorship.sponsorshipType,
        src = sponsorship.sponsorLogo,
        dimensions = sponsorship.sponsorLogoDimensions,
        link = sponsorship.sponsorLink
      ),
      logoForDarkBackground = sponsorship.highContrastSponsorLogo.map { src =>
        Logo.make(
          title = webTitle,
          sponsorshipType = sponsorship.sponsorshipType,
          src,
          dimensions = sponsorship.highContrastSponsorLogoDimensions,
          link = sponsorship.sponsorLink
        )
      },
      aboutThisLink = sponsorship.aboutLink getOrElse defaultAboutThisLink,
      hostedCampaignColour = campaignColour
    )
  }
}

/**
  * Signifies a paid container with multiple sponsors.
  * The container won't show a logo but will have the paid styling.
  */
case object PaidMultiSponsorBranding extends ContainerBranding
